Page({

  /**
   * 页面的初始数据
   */
  data: {
    category: [],
    shop:[],
    activeIndex: 0,
    spread:[],
  },

  changeTab(event) {
    let { id, index } = event.target.dataset;
    this.setData({
      activeIndex: index
    });

    this.getSecondaryCategory(id);
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
      this.getCategory();
      this.getSecondaryCategory();
    
   
  },
// 跳转页面
  gotoPage(event){
    let {url} =event.currentTarget.dataset;
    let pageType = url.split('?')[0];
    let pagePara = url.split('?')[1];
    console.log(pagePara);
    console.log(pageType);
    if (pageType === "ProductDetail"){
        wx.navigateTo({
          url: '/pages/detail/detail?'+pagePara,
          
        })
    } else if (pageType === "ProductList"){
      wx.switchTab({
        url: '/pages/search/search?' + pagePara,

      })
          
    }
  },

// 请求一级分类信息
  getCategory(){
    wx.request({
      url: 'https://mall.360.cn/app/getAppCategory',
      success: (res) => {
        //console.log(res.data.data.category);
        this.setData({
          category: res.data.data.category

        })
      },
      fail: function (res) { },
    })
  },

// 请求二级分类信息
  getSecondaryCategory(id=1){
    wx.showLoading({
      title: '加载中',
    });

    wx.request({

      url: 'https://mall.360.cn/app/getSecondaryCategory?',
      data: ({
        id: id
      }),
      success: res => {
        //console.log(res.data.data);
        let { secondary, spread } = res.data.data;
        this.setData({
          shop: secondary,
          spread: spread
        })
      }
    })

    wx.hideLoading();
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
    
  }
})