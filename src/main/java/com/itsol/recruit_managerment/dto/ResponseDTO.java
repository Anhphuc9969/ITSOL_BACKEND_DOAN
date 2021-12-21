package com.itsol.recruit_managerment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseDTO<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Metadata metadata;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorMessage errorMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseDTO() {
    }

    public ResponseDTO(Metadata metadata, ErrorMessage errorMessage, T data) {
        this.metadata = metadata;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
