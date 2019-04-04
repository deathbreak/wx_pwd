const CryptoJS = require('../../utils/public.js')
const Base64 = require('../../utils/base64.js') 
const sw = require('../../utils/swselect.js')
const app = getApp()

/*swselect
 style="background:{{ scrollTop === 0 ?'-webkit-linear-gradient(top, rgba(105,195,170, 1), rgba(105,195,170, 0.3))' :( scrollTop<200 ? 'rgba(105,195,170,'+(scrollTop/400+0.3) +')' : 'rgba(105,195,170,1)')  }} "
*/
Page({
  data: {
    //welcome: 'Hello World',
    searchInput: '',
    //userInfo: '',
    pages_info: [],
    list: [
      {
        id: 'life',
        name: '生活',
        open: false,
        length_page: '0',
        pages: []
      },
      {
        id: 'work',
        name: '工作',
        open: false,
        length_page:'0',
        pages: []
      },
      {
        id: 'game',
        name: '娱乐',
        open: false,
        length_page: '0',
        pages: []
      },
      {
        id: 'nav',
        name: '其他',
        open: false,
        length_page: '0',
        pages: []
      }
    ]
  },
  kindToggle: function (e) {
    var id = e.currentTarget.id, list = this.data.list;
    for (var i = 0, len = list.length; i < len; ++i) {
      if (list[i].id == id) {
        list[i].open = !list[i].open
      } else {
        list[i].open = false
      }
    }
    this.setData({
      list: list
    });
  },
  //事件处理函数
  bindViewTap: function () {
    /* wx.navigateTo({
       url: '../logs/logs'
     })*/
  },
  listenerSearchInput: function (e) {
    var that = this
    that.setData({
      searchInput: e.detail.value,
    })
    that.setData({
      ['list[0].pages']: sw.swselect(that.data.searchInput, that.data.pages_info.life),
      ['list[0].length_page']: sw.swselect(that.data.searchInput, that.data.pages_info.life).length,
      ['list[1].pages']: sw.swselect(that.data.searchInput, that.data.pages_info.work),
      ['list[1].length_page']: sw.swselect(that.data.searchInput, that.data.pages_info.work).length,
      ['list[2].pages']: sw.swselect(that.data.searchInput, that.data.pages_info.game),
      ['list[2].length_page']: sw.swselect(that.data.searchInput, that.data.pages_info.game).length,
      ['list[3].pages']: sw.swselect(that.data.searchInput, that.data.pages_info.nav),
      ['list[3].length_page']: sw.swselect(that.data.searchInput, that.data.pages_info.nav).length,
    })  
    //console.log(sw.swselect(that.data.searchInput,that.data.pages_info.life))
    //console.log(e.detail.value)
    //console.log(searchInput)
  },
  onPullDownRefresh: function () {
    wx.reLaunch({
      url: '../index/index',
    })
    wx.showToast({
      title: 'loading....',
      icon: 'loading'
    })
    wx.stopPullDownRefresh()
  },
  toSearch: function (e) {
   /* var that = this;
    wx.request({
      //url: 'https://www.deathbreaktest.club/',
      url: 'http://192.168.0.117:9999/pwd/getmsg',
      data: {
        searchInput: that.data.searchInput
      },
      success: function (res) {
        //if (res.data.code == 0) {
        //console.log(res)
        console.log(res.data.id + res.data.msg)
        that.setData({
          //welcome: res.data.data
        });

      }
    })
    */
    //console.log(e)
    //console.log(that.data.searchInput)
  },
 /* toadd: function () {
    wx.navigateTo({
      url: '../add/add'
    })
  },
  toupdate: function () {
    wx.navigateTo({
      url: '../update/update'
    })
  },*/
  onLoad: function () {
    var that = this
    let userInfo = wx.getStorageSync('userInfo')
    var login_method = CryptoJS.Encrypt(Base64.encode('getall_index_onload'));
    wx.request({
      url: 'http://192.168.0.117:9999/pwd/' + login_method,
      data: {
        usertimeid: userInfo
      },
      success: function (re) {
        if (re.data.msg == 'error_info_index_onload') {
          wx.showModal({
            content: '登录态失效,小程序功能暂时无法使用qwq,点击确定重新登录',
            showCancel: false,
            success: function (res) {
              if (res.confirm) {
                wx.reLaunch({
                  url: '../start/start'
                })
              }
            }
          });
        } else {          
          //console.log(re.data.life)
          //var life = 'list[0].pages'
          //console.log(that.data.pages_info)
          that.data.pages_info = re.data;
          //console.log(re.data)
          that.data.pages_info.life = sw.sw_decode(that.data.pages_info.life);
          that.data.pages_info.work = sw.sw_decode(that.data.pages_info.work);
          that.data.pages_info.game = sw.sw_decode(that.data.pages_info.game);
          that.data.pages_info.nav = sw.sw_decode(that.data.pages_info.nav);
          /*console.log(that.data.pages_info)
          ['list[1].pages']: re.data.work,
          ['list[1].length_page']: Object.keys(re.data.work).length,*/
          //console.log(that.data.pages_info.life.length)
          that.setData({
            ['list[0].pages']: that.data.pages_info.life,
            ['list[0].length_page']: that.data.pages_info.life.length,
            ['list[1].pages']: that.data.pages_info.work,
            ['list[1].length_page']: that.data.pages_info.work.length,
            ['list[2].pages']: that.data.pages_info.game,
            ['list[2].length_page']: that.data.pages_info.game.length,
            ['list[3].pages']: that.data.pages_info.nav,
            ['list[3].length_page']: that.data.pages_info.nav.length,
          })    
         // console.log(that.data.list2);     
         // var length = Object.keys(re.data.life).length
         // console.log(length)
        }
      },
      fail: function () {
        wx.showModal({
          content: '与服务器连接异常,小程序功能暂时无法使用qwq,点击确定重试或者退出小程序',
          showCancel: false,
          success: function (res) {
            if (res.confirm) {
              wx.reLaunch({
                url: '../start/start'
              })
            }
          }
        });
      }
    })
  }
})
