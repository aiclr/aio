// 字体随窗口大小改变
const docEL = document.documentElement;

function set_rem_unit() {
    const clientWidth = docEL.clientWidth;
    let rem;
    if (clientWidth < 576) {
        rem = clientWidth / 5;
    }
    if (clientWidth >= 576 && clientWidth < 768) {
        rem = clientWidth / 7;
    }
    if (clientWidth >= 768 && clientWidth < 992) {
        rem = clientWidth / 9;
    }
    if (clientWidth >= 992 && clientWidth < 1200) {
        rem = clientWidth / 14;
    }
    if (clientWidth >= 1200 && clientWidth < 1400) {
        rem = clientWidth / 17;
    }
    if (clientWidth >= 1400 && clientWidth < 1920) {
        rem = clientWidth / 21;
    }
    if (clientWidth >= 1920 && clientWidth < 2560) {
        rem = clientWidth / 25.6;
    }
    if (clientWidth >= 2560) {
        rem = clientWidth / 51.2;
    }
    docEL.style.fontSize = rem + 'px';
}

set_rem_unit();

window.addEventListener('resize', set_rem_unit);
window.addEventListener('pageshow', function e() {
    if (e.persisted) {
        set_rem_unit();
    }
})