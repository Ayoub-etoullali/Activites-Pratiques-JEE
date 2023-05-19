package com.etoullali.exceptions;

/** Exception :
 *      1) RuntimeException: Exception surveillé (add throw Exception)
 *      2) Exception: Exception non surveillé
 */
public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
