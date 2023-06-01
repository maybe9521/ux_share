function dateFormat(n){
    let date = new Date(n);
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes() > 9 ? date.getMinutes() : ("0" + date.getMinutes());
    let second = date.getSeconds() > 9 ? date.getSeconds() : ("0" + date.getSeconds());
    return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
}