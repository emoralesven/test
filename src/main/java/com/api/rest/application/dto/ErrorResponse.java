package com.api.rest.application.dto;

public  class ErrorResponse {
        private String mensaje;

        public ErrorResponse(String key, String message) {
            this.mensaje = message;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }