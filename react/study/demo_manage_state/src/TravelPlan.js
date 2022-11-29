import { useState } from 'react';
import { useImmer } from 'use-immer';
import { initialTravelPlan } from './places.js';

export function TravelPlan() {
  const [plan, setPlan] = useState(initialTravelPlan);

  function handleComplete(parentId, childId) {
    const parent = plan[parentId];
    // Create a new version of the parent place
    // that doesn't include this child ID.
    const nextParent = {
      ...parent,
      childIds: parent.childIds
        .filter(id => id !== childId)
    };
    // Update the root state object...
    setPlan({
      ...plan,
      // ...so that it has the updated parent.
      [parentId]: nextParent
    });
  }

  const root = plan[0];
  const planetIds = root.childIds;
  return (
    <>
      <h2>Places to visit</h2>
      <ol>
        {planetIds.map(id => (
          <PlaceTree
            key={id}
            id={id}
            parentId={0}
            placesById={plan}
            onComplete={handleComplete}
          />
        ))}
      </ol>
    </>
  );
}

export function TravelPlanUseImmer() {
    const [plan, updatePlan] = useImmer(initialTravelPlan);
  
    function handleComplete(parentId, childId) {
      updatePlan(draft => {
        // Remove from the parent place's child IDs.
        const parent = draft[parentId];
        parent.childIds = parent.childIds
          .filter(id => id !== childId);
  
        // Forget this place and all its subtree.
        deleteAllChildren(childId);
        function deleteAllChildren(id) {
          const place = draft[id];
          place.childIds.forEach(deleteAllChildren);
          //delete 删除数组中元素 成功返回true 失败返回 false，数组长度不变 但是 该原数已经不存在数组中
          delete draft[id];
        }
      });
    }
  
    const root = plan[0];
    const planetIds = root.childIds;
    return (
      <>
        <h2>Places to visit</h2>
        <ol>
          {planetIds.map(id => (
            <PlaceTree
              key={id}
              id={id}
              parentId={0}
              placesById={plan}
              onComplete={handleComplete}
            />
          ))}
        </ol>
      </>
    );
  }

// 递归
function PlaceTree({ id, parentId, placesById, onComplete }) {
  const place = placesById[id];
  const childIds = place.childIds;
  return (
    <li>
      {place.title}
      <button onClick={() => {
        onComplete(parentId, id);
      }}>
        Complete
      </button>
      {childIds.length > 0 &&
        <ol>
          {childIds.map(childId => (
            <PlaceTree
              key={childId}
              id={childId}
              parentId={id}
              placesById={placesById}
              onComplete={onComplete}
            />
          ))}
        </ol>
      }
    </li>
  );
}
