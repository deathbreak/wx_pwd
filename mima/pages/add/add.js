// pages/add/add.js
const CryptoJS = require('../../utils/public.js')
const Base64 = require('../../utils/base64.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showTopTips: false,
    user:'',
    pwd:'',
    tips:'',
    radioItems: [
      { name: '生活', value: '生活', checked: true},
      { name: '工作', value: '工作' },
      { name: '娱乐', value: '娱乐' },
      { name: '其他', value: '其他' }
    ],
    remarks:'',
    feilei:'生活',
    n1: '0',
    n2: '0',
    n3: '0',
    n4: '0'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  },
  showTopTips: function () {
    var that = this;
    //console.log(that.data.user + '/' + that.data.pwd + '/' + that.data.tips + '/' + that.data.feilei + '/' + that.data.remarks)
    if (that.data.user == '' || that.data.pwd == '' || that.data.tips == '' || that.data.n1 > 30 || that.data.n2 > 30 || that.data.n3 > 40 || that.data.n4>80){
    this.setData({
      showTopTips: true
    });
    setTimeout(function () {
      that.setData({
        showTopTips: false
      });
    }, 2500);
    }else{
          //console.log('通过')
         //------------------
         //console.log(CryptoJS.Encrypt(Base64.encode('login')))
          var add_method = CryptoJS.Encrypt(Base64.encode('add'));
          var new_user = CryptoJS.Encrypt(Base64.encode(that.data.user));
          var new_pwd = CryptoJS.Encrypt(Base64.encode(that.data.pwd));
          var new_tips = CryptoJS.Encrypt(Base64.encode(that.data.tips));
          var new_feilei = CryptoJS.Encrypt(Base64.encode(that.data.feilei));
          var new_remarks = CryptoJS.Encrypt(Base64.encode(that.data.remarks));
     /* var post_data =that.data.user + '&pwd=' + that.data.pwd + '&tips=' + that.data.tips + '&feilei=' + that.data.feilei + '&remarks=' + that.data.remarks;
      var en_post_data = CryptoJS.Encrypt(Base64.encode(post_data))*/
     // console.log(en_post_data)
          let userInfo = wx.getStorageSync('userInfo')
          //console.log(add_method)
            wx.request({
              url: 'http://192.168.0.117:9999/pwd/' + add_method,
              data: {
                user: new_user,
                pwd: new_pwd,
                tips: new_tips,
                feilei: new_feilei,
                remarks: new_remarks,
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
                } else if (re.data.msg == 'error_over_time'){
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
                } else if (re.data.msg == 'error_it_repeat') {
                  wx.showModal({
                    content: '已储存相同信息,添加失败,点击确定重新添加',
                    showCancel: false,
                    success: function (res) {
                      if (res.confirm) {
                        wx.reLaunch({
                          url: '../add/add'
                        })
                      }
                    }
                  });
                } else if (re.data.msg == 'success_response') {            
                  wx.showModal({
                    content: '添加成功',
                    showCancel: false,
                    success: function (res) {
                      if (res.confirm) {
                        wx.reLaunch({
                          url: '../index/index'
                        })
                      }
                    }
                  });

                      
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
  },
  listenerSearchInput_user: function (e) {
    this.setData({     
        user: e.detail.value,
        n1: e.detail.value.length   
    })  
  },
  listenerSearchInput_pwd: function (e) {
    this.setData({
      pwd: e.detail.value,
      n2: e.detail.value.length  
    })
  },
  listenerSearchInput_tips: function (e) {
    this.setData({
      tips: e.detail.value,
      n3: e.detail.value.length   
    })
  },
  listenerSearchInput_remarks: function (e) {
    this.setData({
      remarks: e.detail.value,
      n4: e.detail.value.length  
    })
  },
  radioChange: function (e) {
    this.setData({
      feilei: e.detail.value
    })
    var radioItems = this.data.radioItems;
    for (var i = 0, len = radioItems.length; i < len; ++i) {
      radioItems[i].checked = radioItems[i].value == e.detail.value;
    }

    this.setData({
      radioItems: radioItems
    });
  }

})