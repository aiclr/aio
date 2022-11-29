//impure function 改变了 stories 使结果难以预测 每次调用都会 push {id: 'create', label: 'Create Story'}
//目的是仅 push 一次 {id: 'create', label: 'Create Story'}
export function StoryTray({stories}) {
    stories.push({
        id: 'create',
        label: 'Create Story'
    });

    return (
        <ul>
            {stories.map(story => (
                <li key={story.id}>
                    {story.label}
                </li>
            ))}
        </ul>
    );
}

//使用局部变量处理 拷贝一份 stories
export function StoryTray1({stories}) {
    let localStories = stories.slice()
    localStories.push({
        id: 'create',
        label: 'Create Story'
    });

    return (
        <ul>
            {localStories.map(story => (
                <li key={story.id}>
                    {story.label}
                </li>
            ))}
        </ul>
    );
}

//不修改 stories
export function StoryTray2({stories}) {
    return (
        <ul>
            {stories.map(story => (
                <li key={story.id}>
                    {story.label}
                </li>
            ))}
            <li key='create'>Create Story</li>
        </ul>
    );
}
