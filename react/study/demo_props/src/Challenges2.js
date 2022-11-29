// 根据大小 显示不同尺寸图片

// 获取设备 DPI
const ratio = window.devicePixelRatio;

function getImageUrl(person, size) {
    // logo192.png
    return (
        person.imageId +
        size + '.png'
    )
}

function Avatar({person, size}) {
    let thumbnailSize = '192';
    if (size * ratio > 90) {
        thumbnailSize = '512';
    }

    return (
        <img
            className="avatar"
            src={getImageUrl(person, thumbnailSize)}
            alt={person.name}
            width={size}
            height={size}
        />
    );
}

export default function Challenges2() {
    return (
        <>
            <Avatar
                size={40}
                person={{
                    name: 'Gregorio Y. Zara',
                    imageId: 'logo'
                }}
            />
            <Avatar
                size={70}
                person={{
                    name: 'Gregorio Y. Zara',
                    imageId: 'logo'
                }}
            />
            <Avatar
                size={120}
                person={{
                    name: 'Gregorio Y. Zara',
                    imageId: 'logo'
                }}
            />
        </>
    );
}