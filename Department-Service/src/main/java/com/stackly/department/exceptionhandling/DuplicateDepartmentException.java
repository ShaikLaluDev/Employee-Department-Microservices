
package com.stackly.department.exceptionhandling;

public class DuplicateDepartmentException extends RuntimeException {

    public DuplicateDepartmentException(String message) {
        super(message);
    }
}
