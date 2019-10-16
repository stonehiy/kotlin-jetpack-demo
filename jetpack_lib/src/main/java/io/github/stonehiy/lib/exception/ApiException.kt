package io.github.stonehiy.lib.exception

import java.lang.Exception

class ApiException constructor() : Exception() {
    constructor(message: String) : this()

}