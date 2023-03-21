package com.gbtech.server

import com.gbtech.FreightRequest
import com.gbtech.FreightResponse
import com.gbtech.FreightServiceGrpc
import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import kotlin.random.Random

@Singleton
class FreightGrpcServer : FreightServiceGrpc.FreightServiceImplBase() {

    private val logger = LoggerFactory.getLogger(FreightGrpcServer::class.java)

    override fun quote(request: FreightRequest?, responseObserver: StreamObserver<FreightResponse>?) {

        logger.info("Quoting freight for: $request")

        val freightResponse = FreightResponse
            .newBuilder()
            .setPostalCode(request?.postalCode)
            .setPrice(Random.nextDouble(from = 1.0, until = 99.0))
            .build()

        logger.info("Quoted freight: $freightResponse")

        responseObserver?.onNext(freightResponse)
        responseObserver?.onCompleted()


    }

}