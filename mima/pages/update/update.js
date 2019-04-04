// pages/update/update.js
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
    check_username:'',
    check_searchword:'',
    accounts: ["生活", "工作", "娱乐", "其他"],
    accountIndex: 1,
    n1: '0',
    n2: '0',
    n3: '0',
    n4: '0'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    that.setData({
    searchword: options.searchword,
    category_pwd: options.category_pwd,
    username: options.username,
    password: options.password,
    remarks: options.remarks,
    check_username: options.username,
    check_searchword: options.searchword
    })
    switch (options.category_pwd) {
      case "生活": that.setData({ accountIndex: 0 }); break;
      case "工作": that.setData({ accountIndex: 1 }); break;
      case "娱乐": that.setData({ accountIndex: 2 }); break;
      case "其他": that.setData({ accountIndex: 3 }); break;
      default: that.setData({ accountIndex: 0 }); break;
    }
  },
  listenerSearchInput_user: function (e) {
    this.setData({
      username: e.detail.value,
      n1: e.detail.value.length  
    })
  },
  listenerSearchInput_pwd: function (e) {
    this.setData({
      password: e.detail.value,
      n2: e.detail.value.length 
    })
  },
  listenerSearchInput_tips: function (e) {
    this.setData({
      searchword: e.detail.value,
      n3: e.detail.value.length  
    })
  },
  listenerSearchInput_remarks: function (e) {
    this.setData({
      remarks: e.detail.value,
      n4: e.detail.value.length  
    })
  },
  bindAccountChange: function (e) {

    var that = this
    that.setData({
      accountIndex: e.detail.value
    })
    switch (e.detail.value) {
      case "0": that.setData({ category_pwd: '生活' }); break;
      case "1": that.setData({ category_pwd: '工作' }); break;
      case "2": that.setData({ category_pwd: '娱乐' }); break;
      case "3": that.setData({ category_pwd: '其他' }); break;
      default: that.setData({ category_pwd: '生活' }); break;
    }
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
  showTopTips: function () {
    var that = this;
    //console.log(that.data.user + '/' + that.data.pwd + '/' + that.data.tips + '/' + that.data.feilei + '/' + that.data.remarks)
    if (that.data.username == '' || that.data.password == '' || that.data.searchword == '' || that.data.n1 > 30 || that.data.n2 > 30 || that.data.n3 > 40 || that.data.n4 > 80) {
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
        content: '确认修改么？',
        showCancel: true,
        success: function (re) {
          if (re.confirm) {
            //console.log(CryptoJS.Encrypt(Base64.encode('login')))
            var update_method = CryptoJS.Encrypt(Base64.encode('update_info'));
            var new_user = CryptoJS.Encrypt(Base64.encode(that.data.username));
            var new_pwd = CryptoJS.Encrypt(Base64.encode(that.data.password));
            var new_tips = CryptoJS.Encrypt(Base64.encode(that.data.searchword));
            var new_feilei = CryptoJS.Encrypt(Base64.encode(that.data.category_pwd));
            var new_remarks = CryptoJS.Encrypt(Base64.encode(that.data.remarks));
            var new_check_username = CryptoJS.Encrypt(Base64.encode(that.data.check_username));
            var new_check_searchword = CryptoJS.Encrypt(Base64.encode(that.data.check_searchword));
            /* var post_data =that.data.user + '&pwd=' + that.data.pwd + '&tips=' + that.data.tips + '&feilei=' + that.data.feilei + '&remarks=' + that.data.remarks;
             var en_post_data = CryptoJS.Encrypt(Base64.encode(post_data))*/
            // console.log(en_post_data)
            let userInfo = wx.getStorageSync('userInfo')
            //console.log(add_method)
            wx.request({
              url: 'http://192.168.0.117:9999/pwd/' + update_method,
              data: {
                user: new_user,
                pwd: new_pwd,
                tips: new_tips,
                feilei: new_feilei,
                remarks: new_remarks,
                usertimeid: userInfo,
                check_username: new_check_username,
                check_searchword: new_check_searchword
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
                    content: '数据库中已经存在你修改后的信息，修改失败',
                    showCancel: false,
                    success: function (res) {
                      if (res.confirm) {
                        wx.reLaunch({
                          url: '../index/index'
                        })
                      }
                    }
                  });
                } else if (re.data.msg == 'success_response') {
                  wx.showModal({
                    content: '修改成功',
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
          }
        }
      });
      //------------------
      
      //------------------
    }
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})