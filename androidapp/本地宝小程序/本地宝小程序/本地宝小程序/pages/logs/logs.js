Page({

  /**
   * 页面的初始数据
   */
  data: {
    editTest:"编辑",
    checkTrue:false,
    cartList:[],
    allPrice:0
  },
  handleEdit(){
    var txt = this.data.editTest == "编辑" ? "完成" : "编辑";
    this.setData({
      editTest:txt
    })
  },

  // 点击全选按钮，item-radio会变化
  handleAll(){

    var checkTrue = !this.data.checkTrue;
    this.setData({
      checkTrue
    })

    var arr = this.data.cartList;
    arr.forEach((v,i) =>{
      v.singleChecked = checkTrue
    })

    this.setData({
      cartList:arr
    })

    this.getAllPrice()
  },

  //计算总价
  getAllPrice(){
    var price = 0;
    var arr = this.data.cartList;
    arr.forEach((v, i) => {
      if (v.singleChecked) {
        price+=v.num*v.price
      }
    })

    this.setData({
      allPrice:price
    })


  },
  //商品前的单选按钮
  handleSingleCheck(e){
    var index = e.currentTarget.dataset.index;
    var arr = this.data.cartList;
    arr[index].singleChecked = !arr[index].singleChecked;
    this.setData({
      cartList:arr
    })

    var flag = true;
    var arr = this.data.cartList;
    arr.forEach((v,i)=>{
      if(v.singleChecked == false){
        flag = false;
        return;
      }
    })

    this.setData({
      checkTrue:flag
    })

    this.getAllPrice()

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
    wx.getStorage({
      key: 'cartList',
      success:(res)=> {
        //console.log(res)
        this.setData({
          cartList:res.data
        })
      },
    })
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