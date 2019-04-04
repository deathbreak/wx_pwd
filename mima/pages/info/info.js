const CryptoJS = require('../../utils/public.js')
const Base64 = require('../../utils/base64.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
        showTopTips: false,
        username:'',
        password:'',
        beizhu:'',
        f:true,
        n:0,
        key:'',

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },
  listenerSearchInput: function (e) {
    this.setData({
      key: e.detail.value,
      n: e.detail.value.length
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },
  ////////////////////////////

  showTopTips: function () {
    var that = this;
    that.setData({
      f:true
    })
    if (that.data.key == ''|| that.data.n > 30) {
      this.setData({
        showTopTips: true
      });
      setTimeout(function () {
        that.setData({
          showTopTips: false
        });
      }, 2500);
    } else {
      
      wx.showModal({
        content: '获取分享信息？',
        showCancel: true,
        success: function (re) {
          if (re.confirm) {

      var getshare_method = CryptoJS.Encrypt(Base64.encode('getshare'));
      var new_key = CryptoJS.Encrypt(Base64.encode(that.data.key));
      let userInfo = wx.getStorageSync('userInfo')
      wx.request({
        url: 'http://192.168.0.117:9999/pwd/' + getshare_method,
        data: {
          key: new_key,
          usertimeid: userInfo
        },
        success: function (re) {
          if (re.data.msg == 'error_no_usertimeid') {
            wx.showModal({
              content: '登录key失效,小程序功能暂时无法使用qwq,点击确定重新登录或者退出小程序',
              showCancel: false,
              success: function (res) {
                if (res.confirm) {
                  wx.reLaunch({
                    url: '../start/start'
                  })
                }
              }
            });
          } else if (re.data.msg == 'error_over_time') {
            wx.showModal({
              content: '登录时长超过30分钟,小程序功能暂时无法使用qwq,点击确定重新登录或者退出小程序',
              showCancel: false,
              success: function (res) {
                if (res.confirm) {
                  wx.reLaunch({
                    url: '../start/start'
                  })
                }
              }
            });
          } else if (re.data.msg == 'error_it_no_key') {
            wx.showModal({
              content: '没有与此密令对应的信息，请重新输入',
              showCancel: false,
              success: function (res) {
                if (res.confirm) {
                  
                }
              }
            });
          } else{
                //console.log(re)
                
                that.setData({
                  f:false,
                  username: Base64.decode(CryptoJS.Decrypt(re.data.life)),
                  password: Base64.decode(CryptoJS.Decrypt(re.data.work)),
                  beizhu: Base64.decode(CryptoJS.Decrypt(re.data.game))
                })
                

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
      //------------------
          }
        }
      });
    }
  },

  ////////////////////////////
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})