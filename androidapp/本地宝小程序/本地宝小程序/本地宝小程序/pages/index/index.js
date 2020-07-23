Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgUrls: [],
    indicatorDots: true,
    vertical: false,
    autoplay: true,
    interval: 2000,
    duration: 500,
    serverArray:[],
    
    productList: [
      {
        "id": "001",
        "imgurl": "http://img5.imgtn.bdimg.com/it/u=360930211,517368292&fm=26&gp=0.jpg",
        "title": "金钱酥",
        "info": "金钱酥-广州情-酥饼礼盒",
        "price": "29.00",
        "oldPrice": "36.00"
      },
      {
        "id": "002",
        "imgurl": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571168373643&di=d3f3d556c170c21891601a0caaa2b2c7&imgtype=0&src=http%3A%2F%2Fimg5.21food.cn%2Fimg%2Falbum%2F2017%2F9%2F21%2Ffood13548241428042Pe.jpg",
        "title": "广式腊肠",
        "info": "广式腊肠广东皇上皇特产添福400g香肠腊肉广州甜味送礼腊味煲仔饭",
        "price": "24.00",
        "oldPrice": "28.00"
      },
      {
        "id": "003",
        "imgurl": "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1770282035,2654745903&fm=15&gp=0.jpg",
        "title": "肉松酥",
        "info": "肉松酥-广州特产手信礼盒",
        "price": "29.00",
        "oldPrice": "38.00"
      },
      {
        "id": "004",
        "imgurl": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571168646810&di=ead4c51ee7404da18f5c45d6d34bb02d&imgtype=0&src=http%3A%2F%2Fwww.xplian.net%2FtuxpJDA0JDI0L1RCMXFJRFlScCQ2YUphRiQ1JDM.jpg",
        "title": "蛋卷",
        "info": "广州陶陶居-蛋卷-广州陶陶居酒家特产糕点零食送礼手信礼盒广东小吃广式点心大礼包",
        "price": "39.00",
        "oldPrice": "48.00"

      }
    ]
    
  },

  
 

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getSwiper();
    this.getServerArray();
    
  },
  // 跳转页面
  gotoPage(event) {
    
    let { url } = 'https://locally.uieee.com/categories/+"event.currentTarget.dataset"+/shops';
    //console.log(event.currentTarget.dataset);
    //console.log(event);
    wx.navigateTo({
      url: '/pages/list/list',
      
      success: function (res) {
        // 通过eventChannel向被打开页面传送数据
        res.eventChannel.emit('Page', { data: event.currentTarget.dataset})
      }
    })
  },

  getSwiper(){
    wx.request({
      url: 'https://locally.uieee.com/slides',
      success:(res)=>{
        //console.log(res.data);
        this.setData({
          imgUrls:res.data
        })
      }
    })

  },


  getServerArray(){
    wx.request({
      url:'https://locally.uieee.com/categories',
      
      success:(res)=>{
        //console.log(res.data); 
        this.setData({
          serverArray: res.data
        })
        
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
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})