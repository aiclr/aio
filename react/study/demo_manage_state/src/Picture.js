import { useState } from "react";
import './Picture.css'

export function Picture() {
    const [isActive, setIsActive] = useState(false);
  
    // 渲染其实是调用此方法 所以 每次 局部变量会被重置
    let backgroundClassName = 'background';
    let pictureClassName = 'picture';
    if (isActive) {
      pictureClassName += ' picture--active';
    } else {
      backgroundClassName += ' background--active';
    }
  
    return (
      <div
        className={backgroundClassName}
        onClick={() => setIsActive(false)}
      >
        <img
          onClick={e => {
            e.stopPropagation();
            setIsActive(true);
          }}
          className={pictureClassName}
          alt="Rainbow houses in Kampung Pelangi, Indonesia"
          src="./logo192.png"
        />
      </div>
    );
  }
  