package com.offsec.nhterm.bridge;

import android.content.Intent;

public class Bridge {
    
    public static Intent createExecuteIntent(String executablePath, String command) {
        Intent intent = new Intent();
        intent.setAction("com.offsec.nhterm.EXECUTE");
        intent.putExtra("executable_path", executablePath);
        intent.putExtra("command", command);
        return intent;
    }
    
    public static Intent createExecuteIntent(SessionId sessionId, String executablePath, String command) {
        Intent intent = createExecuteIntent(executablePath, command);
        if (sessionId != null) {
            intent.putExtra("session_id", sessionId.toString());
        }
        return intent;
    }
}
