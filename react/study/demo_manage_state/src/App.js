import { Form } from './Reacting2Input'
import { Picture } from './Picture';
import { ProfileEditor } from './ProfileEditor';
import { FeedbackForm } from './FeedbackForm';
import { Menu } from './AvoidDuplicationState';
import { TravelPlan, TravelPlanUseImmer } from './TravelPlan';
import { MailClient, MailClient2 } from './MailClient';
import { SyncedInputs } from './SyncedInputs';
import { FilterableList } from './FilterableList';
import { StateTiedToPosition, StatePosition, SamePositionNoReset, SamePositionReset, SamePositionReset2, MyComponent, Scoreboard, Scoreboard2, Scoreboard3, Messenger, KeepState, SwapForm, ContactManager,Gallery,ContactList3} from './ResetState';

export default function App() {

    return (
        <>
            <ContactList3 /><hr />
            <Gallery /><hr />
            <ContactManager /><hr />
            <SwapForm /><hr />
            <KeepState /><hr />
            <Messenger /><hr />
            <MyComponent /><hr />
            <StateTiedToPosition /><hr />
            <StatePosition /><hr />
            <SamePositionNoReset /><hr />
            <SamePositionReset /><hr />
            <SamePositionReset2 /><hr />
            <Scoreboard /><hr />
            <Scoreboard2 /><hr />
            <Scoreboard3 /><hr />
            <p>Reacting to input with state</p>
            <Form /><hr />
            <Picture /><hr />
            <ProfileEditor /><hr />
            <FeedbackForm /><hr />
            <Menu /><hr />
            <SyncedInputs /><hr />
            <FilterableList /><hr />
            <MailClient /><hr />
            <MailClient2 /><hr />
            <TravelPlan /><hr />
            <TravelPlanUseImmer /><hr />
        </>
    );
}
