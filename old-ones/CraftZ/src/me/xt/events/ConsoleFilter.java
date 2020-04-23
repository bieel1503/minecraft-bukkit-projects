package me.xt.events;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.Message;

public class ConsoleFilter implements Filter{
	
	

    @Override
    public Result filter(LogEvent event) {
    	String msg = event.getMessage().toString().toLowerCase();
    	String i = "issued server command: ";
        if (msg.contains(i + "/login") || msg.contains(i + "/logar") || msg.contains(i + "/register")
        		|| msg.contains(i + "/registrar") || msg.contains(i + "/changepassword") || msg.contains(i + "/trocarsenha")
        		|| msg.contains("excedeu o tempo") || msg.contains("uma conta no server ao mesmo tempo")
        		|| msg.contains("entre novamente com a nova senha.") || msg.contains("administrador deletou")
        		|| msg.contains("3 vezes...")){
            return Result.DENY;
        }
        return null;
	}

	@Override
	public Result filter(org.apache.logging.log4j.core.Logger arg0, Level arg1, Marker arg2, String arg3,
			Object... arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result filter(org.apache.logging.log4j.core.Logger arg0, Level arg1, Marker arg2, Object arg3,
			Throwable arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result filter(org.apache.logging.log4j.core.Logger arg0, Level arg1, Marker arg2, Message arg3,
			Throwable arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getOnMatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getOnMismatch() {
		// TODO Auto-generated method stub
		return null;
	}

}
