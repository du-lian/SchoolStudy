let fs = require('fs')
fs.unlink('dulian.txt',(err)=>{
    if(err) throw err;
    console.log("成功删除!")
})