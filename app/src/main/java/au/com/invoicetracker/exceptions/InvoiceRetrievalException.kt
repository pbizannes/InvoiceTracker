package au.com.invoicetracker.exceptions

class InvoiceRetrievalException: Exception {
    // Secondary constructor delegating to the primary constructor
    constructor() : super()

    // Another secondary constructor, also delegating
    constructor(throwable: Throwable) : super(throwable)

    // Another secondary constructor, also delegating
    constructor(message: String) : super(message)
}