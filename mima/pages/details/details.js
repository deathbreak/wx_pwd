// pages/details/details.js
const CryptoJS = require('../../utils/public.js')
const Base64 = require('../../utils/base64.js') 
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showTopTips: false,
    searchword: '',
    category_pwd: '',
    username: '',
    password: '',
    remarks: '',
    f:true,
    n1:0,
    n2:0,
    s:'',
    b:'这个家伙很懒，没有写备注!'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      searchword: options.searchword,
      category_pwd: options.category_pwd,
      username: Base64.decode(CryptoJS.Decrypt(options.username)),
      password: Base64.decode(CryptoJS.Decrypt(options.password)),
      remarks:  Base64.decode(CryptoJS.Decrypt(options.remarks))
    })
  },
  listenerSearchInput_s: function (e) {
    this.setData({
      s: e.detail.value,
      n1: e.detail.value.length
    })
  },
  listenerSearchInput_b: function (e) {
    this.setData({
      b: e.detail.value,
      n2: e.detail.value.length
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  todelete: function () {
    var that = this
    wx.showModal({
      content: '确定删除么？',
      showCancel: true,
      success: function (re) {
        if (re.confirm) {
        
          var del_word = that.data.searchword
          var del_username = that.data.username
          if (del_word == '' || del_username == '') {
           
            wx.reLaunch({
              url: '../index/index'
            })
          } else {
            var new_user = CryptoJS.Encrypt(Base64.encode(that.data.username));
            var new_tips = CryptoJS.Encrypt(Base64.encode(that.data.searchword));
            var delete_method = CryptoJS.Encrypt(Base64.encode('delete_info'));
            let userInfo = wx.getStorageSync('userInfo')
            wx.request({
              url: 'http://192.168.0.117:9999/pwd/' + delete_method,
              data: {
                user: new_user,
                tips: new_tips,
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
                } else if (re.data.msg == 'error_it_null') {
                  wx.showModal({
                    content: '数据库中已没有此信息，删除失败',
                    showCancel: false,
                    success: function (res) {
                      if (res.confirm) {
                        wx.reLaunch({
                          url: '../index/index'
                        })
                      }
                    }
                  });
                } else if (re.data.msg == 'success_delete') {
                  wx.showModal({
                    content: '删除成功',
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
        }
      }
    });
    
      
  },
  toupdate: function () {
    var that = this
    wx.navigateTo({
      url: '../update/update?searchword=' + that.data.searchword + '&category_pwd=' + that.data.category_pwd+'&username='+that.data.username+'&password='+that.data.password+'&remarks='+that.data.remarks
    })
  },
  toshare: function () {
     var that = this
     that.setData({
       f:false
     })
  },
  sharepwd: function () {
   var that = this
   //*****************
    if (that.data.username == '' || that.data.password == '' || that.data.s == '' || that.data.b == '' || that.data.n1 > 30 || that.data.n2 > 30) {
      this.setData({
        showTopTips: true
      });
      setTimeout(function () {
        that.setData({
          showTopTips: false
        });
      }, 2500);
    } else {
      //console.log('通过')
      wx.showModal({
        content: '确认分享么？',
        showCancel: true,
        success: function (re) {
          if (re.confirm) {
      
            var share_method = CryptoJS.Encrypt(Base64.encode('share_info'));
            var new_user = CryptoJS.Encrypt(Base64.encode(that.data.username));
            var new_pwd = CryptoJS.Encrypt(Base64.encode(that.data.password));

            var new_s = CryptoJS.Encrypt(Base64.encode(that.data.s));
            var new_b = CryptoJS.Encrypt(Base64.encode(that.data.b));
                       
            let userInfo = wx.getStorageSync('userInfo')
            
            wx.request({
              url: 'http://192.168.0.117:9999/pwd/' + share_method,
              data: {
                user: new_user,
                pwd: new_pwd,
                key: new_s,
                beizhu: new_b,
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
                } else if (re.data.msg == 'error_it_repeat') {
                  wx.showModal({
                    content: '数据库中已存在你定义的密令，请修改一个不一样的密令',
                    showCancel: false,
                    success: function (res) {
                      if (res.confirm) {
                        that.setData({
                          s:''
                        })
                      }
                    }
                  });
                } else if (re.data.msg == 'success_response') {
                  wx.showModal({
                    content: '分享成功,此条分享信息将于10分钟后自动销毁',
                    showCancel: false,
                    success: function (res) {
                      if (res.confirm) {
                        wx.reLaunch({
                          url: '../info/info'
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
          }
        }
      });
      //------------------

      //------------------
    }
   //*****************
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

  }
})