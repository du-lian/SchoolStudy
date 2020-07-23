// pages/detail/detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    main_item: {},
    content:"",
    ctitle:{},
    activeIndex:1
    
  },
// 点击返回
  backPage(){
    wx.navigateBack();
  },
// 点击跳转
  gotoTab(event){
    
    let {index} = event.currentTarget.dataset;
    this.setData({
      activeIndex:index
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getDatailData(options.item_id)
    
  },

  getDatailData(item_id) {
    wx.request({
      url: 'https://mall.360.cn/shop/itemApi',
      data: {
        item_id
      },
      success: res => {
        let {
          main_item
        } = res.data.data;

        let { content,ctitle} = main_item;
        
        this.setData({
          main_item,
          content,
          ctitle: ctitle.split('_')[1]
        })
      }
    })
  },

  

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})