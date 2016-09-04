package data.com.prism.bank;

public class ShutDownHook extends Thread{

	@Override
	public void run() {
		DataBankMgr.flushMetaIndex();
		DataBankMgr.flushStatus();
	}
}
