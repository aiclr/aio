// 模拟 html 标签写法 创建组件
// <div>
//   <img />
// </div>
// 如下
//<Card>
//  <Avatar />
//</Card>

import {getImageUrl} from "./utils";

export default function MyAvatar({person,size}) {
    return (
            <img
                className="avatar"
                src={getImageUrl(person)}
                alt={person.name}
                width={size}
                height={size}
            />
            );
}