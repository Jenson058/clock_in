import {Key, Value} from "@/common/decoration/BusMap";
import {MetadataCenter} from "@/common/decoration/MetadataCenter";
import 'reflect-metadata'

export class Decoration {

    public static readonly MetadataCenter = "MetadataCenter"

    private static getManager(klass: Function) {
        if (!Reflect.getMetadata(this.MetadataCenter, klass)) {
            Reflect.defineMetadata(this.MetadataCenter, new MetadataCenter(klass), klass)
        }
        return Reflect.getMetadata(this.MetadataCenter, klass) as MetadataCenter

    }


    /**
     * 类装饰器
     */
    public static classAnnotation(
        affiliatedMetadataDomain: Key,
        metadataKey: Key,
        metadataValue?: Value
    ) {
        return (klass: Function) => {
            this.getManager(klass)
                .classMetadataManager
                .add(affiliatedMetadataDomain, klass.name, metadataKey, metadataValue);
        };
    }

    /**
     * 方法装饰器
     */
    public static methodAnnotation(
        affiliatedMetadataDomain: Key,
        metadataKey: Key,
        metadataValue?: Value
    ) {
        return (instance: Function, methodKey: Key) => {
            const {constructor: klass} = instance;
            this.getManager(klass)
                .methodMetadataManager
                .add(affiliatedMetadataDomain, methodKey, klass.name, metadataKey, metadataValue);
        };
    }

    /**
     * 属性装饰器
     */
    public static propertyAnnotation(
        affiliatedMetadataDomain: Key,
        metadataKey: Key,
        metadataValue?: Value
    ) {
        return function (target: any, propertyKey: string) {
            const {constructor: klass} = target
            let manager = Decoration.getManager(klass);
            manager
                .propertyMetadataManager
                .add(affiliatedMetadataDomain, propertyKey, metadataKey, metadataValue)
            manager.propSet
                .set(propertyKey, metadataKey)
        }
    }

    /**
     * 方法参数的注解
     * @param affiliatedMetadataDomain
     * @param metadataKey
     * @param metadataValue
     */
    public static parameterAnnotation(
        affiliatedMetadataDomain: Key,
        metadataKey: Key,
        metadataValue?: Value
    ) {
        return (instance: Function, methodKey: Key, parameterIndex: number) => {
            const {constructor: klass} = instance;
            this.getManager(klass)
                .paramMetadataManager
                .add(affiliatedMetadataDomain, methodKey, parameterIndex, metadataKey, metadataValue);
        }
    }
}