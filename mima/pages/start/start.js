//login.js
const CryptoJS = require('../../utils/public.js')
const Base64 = require('../../utils/base64.js')
//获取应用实例
var app = getApp();
Page({
  data: {
    remind: '加载中',
    angle: 0,
    userInfo: {},
    showit: true 
  },
  goToIndex: function () {
    wx.switchTab({
      url: '/pages/index/index',
    });
  },
  onLoad: function () {
    var that = this
    wx.setNavigationBarTitle({
      title: 'login...'
    })
  },
  onShow: function () {
    let that = this
    let userInfo = wx.getStorageSync('userInfo')
    

    wx.login({
      success: function (res) {
        if (res.code) {
          //发起网络请求
          //console.log(CryptoJS.Encrypt(Base64.encode('login')))
          var login_method = CryptoJS.Encrypt(Base64.encode('login'));
          wx.request({
            url: 'http://192.168.0.117:9999/pwd/' + login_method,
            data: {
              code: res.code
            },
            success: function (re) {
               //console.log(re.data)
              if(re.data.usertimeid=='error_info'){
                wx.showModal({
                  content: '数据异常,小程序功能暂时无法使用qwq,点击确定重试或者退出小程序',
                  showCancel: false,
                  success: function (res) {
                    if (res.confirm) {
                      wx.reLaunch({
                        url: '../start/start'
                      })
                    }
                  }
                });
              }else{ 
                wx.setStorageSync('userInfo', re.data.usertimeid)
                that.setData({
                  userInfo: userInfo,
                  showit: false
                })
                //console.log(userInfo)
              }
            },
            fail:function(){
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
        } else {
          console.log('登录失败！' + res.errMsg)
        }

      }
    })
  },
  onReady: function () {
    var that = this;
    setTimeout(function () {
      that.setData({
        remind: ''
      });
    }, 2000);
    wx.onAccelerometerChange(function (res) {
      var angle = -(res.x * 30).toFixed(1);
      if (angle > 14) { angle = 14; }
      else if (angle < -14) { angle = -14; }
      if (that.data.angle !== angle) {
        that.setData({
          angle: angle
        });
      }
    });
  }
});