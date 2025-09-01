package com.apisendemail.apisendemail.model;

public class Mensagem {
    public static String montarMensagem(String from, String message) {
        if (from == null){
            return from = "Anônimo";
        }

        return """
            <html>
            <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
                <div style="max-width: 600px; background: white; border-radius: 8px; padding: 20px; 
                            box-shadow: 0 2px 5px rgba(0,0,0,0.1); margin: auto;">
                    
                    <h2 style="color: #333; text-align: center;">📩 Nova mensagem recebida</h2>
                    
                    <p style="font-size: 14px; color: #555;">
                        <strong>De:</strong> %s
                    </p>
                    
                    <div style="padding: 15px; background: #f9f9f9; border-radius: 6px; border: 1px solid #eee;">
                        <p style="color: #333; line-height: 1.5; white-space: pre-wrap;">%s</p>
                    </div>
                    
                    <hr style="border: none; border-top: 1px solid #ddd; margin: 20px 0;">
                    
                    <p style="font-size: 12px; color: #888; text-align: center;">
                        Este e-mail foi enviado automaticamente pelo sistema.
                    </p>
                </div>
            </body>
            </html>
        """.formatted(from, message);
    }
}
