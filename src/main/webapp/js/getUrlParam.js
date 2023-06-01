function getUrlParam(urlStr){
	// 定义一个空对象以储存数据
	const urlObj = {}
	// 检查url中是否携带数据
	if (urlStr.indexOf("?") === -1) return null
	// 找到 '?' 对应的下标
	const index = urlStr.indexOf("?")
	// 截取 '?' 后的内容
	const dataStr = urlStr.substr(index + 1)
	// 通过 '&' 将字符串分割成数组
	const dataArr = dataStr.split("&")
	// 遍历字符串分割后的数组
	dataArr.forEach((str) => {
	  // 判断数组内的字符串是否有 '='
	  if (str.indexOf("=") === -1) {
		// 如没有 '=' , 则将此字符串作为对象内键值对的键, 键值对的值为 ''
		urlObj[str] = ""
	  } else {
		// 如果有 '='
		// 通过 '=' 将此字符串截取成两段字符串（不推荐使用 split 分割, 因为数据中可能携带多个 '=' ）
		const innerArrIndex = str.indexOf("=")
		const key = str.substring(0, innerArrIndex)
		const value = str.substr(innerArrIndex + 1)
		// Js判断数组或对象中的key是否存在
		if (!urlObj.hasOwnProperty(key)) {
		  // 以截取后的两段字符串作为对象的键值对  decodeURIComponent将uri格式转换为汉字
		  urlObj[key] = decodeURIComponent(value)
		} else {
		  var oldObj = urlObj[key]
		  // 判断是否为数组
		  if (!(oldObj instanceof Array)) {
			// 不是转换为数组
			oldObj = [urlObj[key]]
		  }
		  oldObj.push(decodeURIComponent(value))
		  urlObj[key] = oldObj
		}
	  }
	})
	return urlObj;
}