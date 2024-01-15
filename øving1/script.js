var count = 0
const count_btn = document.getElementById("increment")
const hide_btn = document.getElementById("hide")
const array_btn = document.getElementById("create")

//function add() {
//    count++
//    document.getElementById("val").innerHTML = count
//}
//count_btn.onclick = function() { add() }

count_btn.onclick = () => {
    count++
    document.getElementById("val").innerHTML = count
}

hide_btn.onclick = () => {
    is_hidden = document.getElementById("text").hidden;
    document.getElementById("text").hidden = !is_hidden
}

array_btn.onclick = () => {
    let names = ["harry", "andreas", "lars", "anders", "henrik", "valdemar", "yasin"]
    let list = document.getElementById("array")
    list.innerHTML = '' 

    for (i = 0; i < 3; i++) {
        let li = document.createElement('li')
        li.innerText = names[Math.floor(Math.random()*7)]
        list.appendChild(li)
    }
}
