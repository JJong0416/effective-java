package lecture04;

import java.util.List;

public interface WithdrawalService {
    void withdrawalMember(Member member);

    List<Member> getMembers();

    WithdrawalProcessType getProcessType();

    default void startProcess() {
        List<Member> members = getMembers();
        members.forEach(this::withdrawalMember);
    }
}