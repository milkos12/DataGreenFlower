let menuToggle = document.querySelector('.toggle');
let nav = document.querySelector('.nav');
let pantalla = document.querySelector(".pantalla");
menuToggle.onclick = function () {
    menuToggle.classList.toggle('active');
    nav.classList.toggle('active');
    pantalla.classList.toggle('active');
}
//active
let list = document.querySelectorAll('.list');
for (let i = 0; i < list.length; i++) {
    list[i].onclick = function () {
        let j = 0;
        while (j < list.length) {
            list[j++].className = 'list';
        }
        list[i].className = 'list active';
    }
}

