public class Main {

    enum State {
        STATE_A,
        STATE_B,
        SUBSTATE_B1,
        SUBSTATE_B2,
        SUBSTATE_B3,
        STATE_C,
        STATE_D,
        STATE_DONE
    }

    public static void main(String[] args) {

        State current_state = State.STATE_A;
        State substate_B = State.SUBSTATE_B1;

        while (current_state != State.STATE_DONE) {
            switch (current_state) {
                case STATE_A:
                    System.out.println("Currently in State A");
                    current_state = State.STATE_B;
                    break;

                case STATE_B:
                    System.out.println("Currently in State B");

                    while (substate_B != State.STATE_C) {
                        switch (substate_B) {
                            case SUBSTATE_B1:
                                System.out.println("   Substate B1");
                                substate_B = State.SUBSTATE_B2;
                                break;

                            case SUBSTATE_B2:
                                System.out.println("   Substate B2");
                                substate_B = State.SUBSTATE_B3;
                                break;

                            case SUBSTATE_B3:
                                System.out.println("   Substate B3");
                                substate_B = State.STATE_C;
                                break;
                        }
                    }
                    current_state = State.STATE_C;
                    break;

                case STATE_C:
                    System.out.println("Currently in State C");
                    current_state = State.STATE_D;
                    break;

                case STATE_D:
                    System.out.println("Currently in State D");
                    current_state = State.STATE_DONE;
                    break;
            }
        }

        System.out.println("State machine execution completed.");
    }
}
