package com.tei.snapshop.data.network

import java.io.IOException

/**
 * Class Description
 * Created by Eniola Ipoola on 15/08/2024.
 * Copyright (c). All rights reserved
 */
// Custom exception for no internet scenario
class NoInternetException(message: String) : IOException(message)