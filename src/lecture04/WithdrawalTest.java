package lecture04;

import java.util.List;

public class WithdrawalTest {

    private class TestWithdrawalService implements WithdrawalService {


        @Override
        public void withdrawalMember(Member member) {

        }

        @Override
        public List<Member> getMembers() {
            return null;
        }

        @Override
        public WithdrawalProcessType getProcessType() {
            return null;
        }

        @Override
        public void startProcess() {
            WithdrawalService.super.startProcess();
        }
    }
}
