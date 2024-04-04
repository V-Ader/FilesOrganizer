package file.processor

sealed trait ProcessingOutcome
case object OutcomeSaved extends ProcessingOutcome
case object OutcomeError extends ProcessingOutcome
case object OutcomeSkipped extends ProcessingOutcome
