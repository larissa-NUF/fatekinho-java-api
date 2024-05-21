package br.com.fatekinho.fatekinho.model.response;

public class LoginResponse {
    private boolean success;
    private long userId; // Supondo que userId é um long
    private String message;

    // Construtor
    public LoginResponse(boolean success, long userId, String message) {
        this.success = success;
        this.userId = userId;
        this.message = message;
    }

    // Getters e Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}