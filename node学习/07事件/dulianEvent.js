let dulian ={
    event:{},
    on:function(eventName,eventFn){
        if(this.event[eventName]){
            this.event[eventName].push(eventFn);
        }else{
            this.event[eventName]=[];
            this.event[eventName].push(eventFn);
        }
    },
    emit:function(eventName,eventMsg){
        if(this.event[eventName]){
            this.event[eventName].forEach(itemFn => {
                itemFn(eventMsg)
            });
        }
    }

}


dulian.on('helloSuccess',(eventMsg)=>{
    console.log("1.数据库查看所有用户详细信息")
})

dulian.on('helloSuccess',(eventMsg)=>{
    console.log("2.统计用户年龄比例")
})

dulian.on('helloSuccess',(eventMsg)=>{
    console.log("3.查看所有用户学校的详细信息")
})

module.exports = dulian