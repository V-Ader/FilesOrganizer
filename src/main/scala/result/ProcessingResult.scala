package result

case class ProcessingResult(succeeded: Int = 0, errors: Int = 0, skipped: Int = 0) {
  def update(outcome: ProcessingOutcome): ProcessingResult = outcome match {
    case OutcomeSaved => copy(succeeded = succeeded + 1)
    case OutcomeError => copy(errors = errors + 1)
    case OutcomeSkipped => copy(skipped = skipped + 1)
  }
}
