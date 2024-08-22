import {BusMap, Key, Value} from "@/common/decoration/BusMap";
import {c} from "vite/dist/node/types.d-FdqQ54oU";

export class MethodMetadata {
    /**
     * 该方法元数据所属的领域
     */
    public readonly affiliatedMetadataDomain: Key;
    /**
     * 所标注的类的方法
     */
    public readonly annotatedMethod: Key;
    public readonly className: Key;

    public readonly metadataKey: Key;
    public readonly metadataValue: Value;

    constructor(
        affiliatedMetadataDomain: Key,
        annotatedMethod: Key,
        className:Key,
        metadataKey: Key,
        metadataValue: Value
    ) {
        this.affiliatedMetadataDomain = affiliatedMetadataDomain;
        this.annotatedMethod = annotatedMethod;
        this.className = className
        this.metadataKey = metadataKey;
        this.metadataValue = metadataValue;
    }
}

/**
 * 方法的元数据管理器
 */
export class MethodMetadataManager {
    /**
     * [Key, Key, Key] 对应 [affiliatedMetadataDomain, annotatedMethod, metadataKey]
     * 当前该属性属性 public 的, 当 find 函数完善之后可以转为 private
     */
    public readonly methodMetadataList: BusMap<[Key, Key, Key, Key], MethodMetadata>;

    constructor() {
        this.methodMetadataList = new BusMap<[Key, Key, Key, Key], MethodMetadata>;
    }

    /**
     * todo 在模糊搜索的基础之上, 扩充一系列常用的精确搜索函数
     * 模糊搜索
     * @param affiliatedMetadataDomain
     * @param annotatedMethod
     * @param metadataKey
     */
    public findAll(affiliatedMetadataDomain?: Key, annotatedMethod?: Key, metadataKey?: Key): Array<MethodMetadata> {
        return this.methodMetadataList.filter(
            ([currAffiliatedMetadataDomain, currAnnotatedProperty, currMetadataKey]) => {
                return (affiliatedMetadataDomain ? currAffiliatedMetadataDomain == affiliatedMetadataDomain : true)
                    && (annotatedMethod ? currAnnotatedProperty == annotatedMethod : true)
                    && (metadataKey ? currMetadataKey == metadataKey : true)
            }
        )
    }

    /**
     * 精确查找
     * @param affiliatedMetadataDomain
     * @param annotatedMethod
     * @param metadataKey
     */
    public find(affiliatedMetadataDomain: Key, annotatedMethod: Key, metadataKey: Key): MethodMetadata | undefined {
        let result = this.findAll(affiliatedMetadataDomain, annotatedMethod, metadataKey);
        if (result.length == 0) return undefined;
        return result[0];
    }

    /**
     * 添加
     * @param affiliatedMetadataDomain
     * @param annotatedMethod
     * @param className
     * @param metadataKey
     * @param metadataValue
     */
    public add(
        affiliatedMetadataDomain: Key,
        annotatedMethod: Key,
        className: Key,
        metadataKey: Key,
        metadataValue: Value
    ): void {
        this.methodMetadataList.set(
            [affiliatedMetadataDomain, annotatedMethod, className, metadataKey],
            new MethodMetadata(affiliatedMetadataDomain, annotatedMethod, className ,metadataKey, metadataValue)
        );
    }

    public getKey(){
        this.methodMetadataList
    }
}