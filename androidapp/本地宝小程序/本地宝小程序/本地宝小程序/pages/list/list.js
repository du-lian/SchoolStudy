// pages/list/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    shopArray: [],
    page: 1,
    limit: 8,
    flag: true,
    id: '1'

  },
  handleGetDetail(e){
    console.log(e)
    // 把获取到的信息存储到storage里面
    var data = e.currentTarget.dataset.listdetail;

    wx.setStorage({
      key: 'listDetail',
      // data:data, 等同于下面的写法
      data
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    const eventChannel = this.getOpenerEventChannel();
    eventChannel.on('Page', function (data) {
      options = data;
    })
    this.setData({
      id: options.data.id
    })
    //console.log(options.data);
    wx.setNavigationBarTitle({
      title: options.data.name,
    })
    this.getShopData(1,this.data.limit,(res)=>{
      console.log(res.data)
      // 把数据写入data里面的shopArray
      this.setData({
        shopArray: res.data
      })
    });


  },

  getShopData(page,limit,callback){
    wx.request({
      url: `https://locally.uieee.com/categories/${this.data.id}/shops`,
      method: "get",
      // 传递给后台的数据
      data: {
        _page: page,
        _limit: limit
      },
      success: (res) => {
        callback(res)
      }
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
    if (!this.data.flag) {
     wx.showToast({
       title: '亲，所有数据加载完毕~',
       icon:'none',
       duration:2000
     })
     return;
    }
    var page = this.data.page;
    page++;
    this.setData({
      page
    })
    //发送请求
    this.getShopData(page,this.data.limit,(res)=>{
      var shopArray = this.data.shopArray;
      // es6的语法： 推荐使用
      var shopArray = [...this.data.shopArray, ...res.data]
      // 把数据写入data里面的list
      this.setData({
        shopArray
      })
      //  4. 当上拉加载值，返回的res.data的长度如果小于 每页显示的条数this.data.limit,则证明数据已经加载完毕
      if (res.data.length < this.data.limit) {
        this.setData({
          flag: false
        })
      }
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})