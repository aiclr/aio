// 如果 props 没有传 type 使用默认值 '.png'
// 如果传 type={undefined} 也使用默认值 '.png'
// 如果传 type={null} 不会使用默认值
// 如果传 type={0} 不会使用默认值
export function getImageUrl(person,type='.png') {
    // logo192.png
    return (
            'logo' +
            person.imageId +
            type
    )
}