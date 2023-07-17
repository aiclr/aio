let startTime = new Date('2021/12/18 20:53:00');

function time() {
    const ms = Math.floor((new Date() - startTime));
    const d = Math.floor(ms / 86400000);
    const h = Math.floor((ms - d * 86400000) / 3600000);
    const m = Math.floor((ms - d * 86400000 - h * 3600000) / 60000);
    const s = Math.floor((ms - d * 86400000 - h * 3600000 - m * 60000) / 1000);

    const daysText = d.toString() + ' days,';
    let hoursText = "";
    let minutesText = "";
    let secondsText = "";

    if (h > 0)
        hoursText = h.toString() + ' hours,';

    if (m > 0 && m < 10) {
        minutesText = '0' + m.toString() + ' minutes and ';
    } else if (m > 9) {
        minutesText = m.toString() + ' minutes and ';
    }

    secondsText = s < 10 ? '0' + s.toString() : s.toString()

    document.getElementById("time").innerHTML = daysText + hoursText + minutesText + secondsText + ' seconds have passed';
}

setInterval(time, 1000);