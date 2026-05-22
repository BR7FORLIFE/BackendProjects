import { Module } from '@nestjs/common';
import { ProductsController } from './infra/controllers/product.controller';
import { ProductUseCaseImp } from './application/orchestator/product-orchestator';
import { ProductRepository } from './infra/repository/product.repository';

@Module({
  controllers: [ProductsController],
  providers: [ProductUseCaseImp, ProductRepository],
})
export class ProductModule {}
