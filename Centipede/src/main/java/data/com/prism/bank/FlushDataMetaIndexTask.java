package data.com.prism.bank;

import java.util.TimerTask;

public class FlushDataMetaIndexTask extends TimerTask{

	@Override
	public void run() {
		DataBankMgr.flushMetaIndex();
		DataBankMgr.flushStatus();
	}
}
