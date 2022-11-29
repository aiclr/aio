import Heading,{Heading2} from './Heading.js';
import Section, {Section2} from './Section.js';

// Use the context 
// 自动从 最近的父 Section 获取 level
export function UseContext() {
  return (
    <Section level={1}>
      <Heading>Title</Heading>
      <Section level={2}>
        <Heading>Heading</Heading>
        <Heading>Heading</Heading>
        <Heading>Heading</Heading>
        <Section level={3}>
          <Heading>Sub-heading</Heading>
          <Heading>Sub-heading</Heading>
          <Heading>Sub-heading</Heading>
          <Section level={4}>
            <Heading>Sub-sub-heading</Heading>
            <Heading>Sub-sub-heading</Heading>
            <Heading>Sub-sub-heading</Heading>
          </Section>
        </Section>
      </Section>
    </Section>
  );
}

export function UseContext2() {
    return (
      <Section2>
        <Heading2>Title</Heading2>
        <Section2>
          <Heading2>Heading</Heading2>
          <Heading2>Heading</Heading2>
          <Heading2>Heading</Heading2>
          <Section2>
            <Heading2>Sub-heading</Heading2>
            <Heading2>Sub-heading</Heading2>
            <Heading2>Sub-heading</Heading2>
            <Section2>
              <Heading2>Sub-sub-heading</Heading2>
              <Heading2>Sub-sub-heading</Heading2>
              <Heading2>Sub-sub-heading</Heading2>
            </Section2>
          </Section2>
        </Section2>
      </Section2>
    );
  }
