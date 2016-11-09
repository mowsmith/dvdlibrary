
package com.tsguild.dvdlibrary.validation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Morgan Smith
 */
public class ValidationErrorContainer {
 private List<ValidationError> validationErrors = new ArrayList<>();

 public void addValidationError(String field, String message) {
 ValidationError error = new ValidationError(field, message);
 validationErrors.add(error);
 }
 public List<ValidationError> getFieldErrors() {
 return validationErrors;
 }
}
