//1、数据不能进行二进制数据操作
//2、js数组不像javapython等语言效率高
//3、buffer内存空间开辟出固定大小的内存
//将字符串转成buffer对象
var str = "helloworld"
let buf = Buffer.from(str)
console.log(buf)

//输出buffer内容
console.log(buf.toString())

//开辟空间
var buf1=Buffer.alloc(10)
//buf1[0]=256   输出结果是0
console.log(buf1)