/**
 * 方法的元数据
 */
import {BusMap, Key, Value} from "@/common/decoration/BusMap";

export class ParamMetadata {
    /**
     * 该方法元数据所属的领域
     */
    public readonly affiliatedMetadataDomain: Key;
    /**
     * 所标注的类的方法
     */
    public readonly annotatedMethod: Key;

    public readonly annotatedMethodParamIndex: number;

    public readonly metadataKey: Key;
    public readonly metadataValue: Value;

    constructor(
        affiliatedMetadataDomain: Key,
        annotatedMethod: Key,
        annotatedMethodParamIndex: number,
        metadataKey: Key,
        metadataValue: Value
    ) {
        this.affiliatedMetadataDomain = affiliatedMetadataDomain;
        this.annotatedMethod = annotatedMethod;
        this.annotatedMethodParamIndex = annotatedMethodParamIndex
        this.metadataKey = metadataKey;
        this.metadataValue = metadataValue;
    }
}

/**
 * 属性的元数据管理器
 */
export class ParamMetadataManager {
    /**
     * [Key, Key, Key] 对应 [affiliatedMetadataDomain, annotatedMethod, metadataKey]
     * 当前该属性属性 public 的, 当 find 函数完善之后可以转为 private
     */
    public readonly paramMetadataList: BusMap<[Key, Key, number, Key], ParamMetadata>;

    constructor() {
        this.paramMetadataList = new BusMap<[Key, Key, number, Key], ParamMetadata>;
    }

    /**
     * todo 在模糊搜索的基础之上, 扩充一系列常用的精确搜索函数
     * 模糊搜索
     * @param affiliatedMetadataDomain
     * @param annotatedMethod
     * @param annotatedMethodParamIndex
     * @param metadataKey
     */
    public findAll(
        affiliatedMetadataDomain?: Key,
        annotatedMethod?: Key,
        annotatedMethodParamIndex?: number,
        metadataKey?: Key
    ): Array<ParamMetadata> {
        return this.paramMetadataList.filter(
            ([currAffiliatedMetadataDomain, currAnnotatedProperty, currMethodParamIndex, currMetadataKey]) => {
                return (affiliatedMetadataDomain ? currAffiliatedMetadataDomain == affiliatedMetadataDomain : true)
                    && (annotatedMethod ? currAnnotatedProperty == annotatedMethod : true)
                    && (annotatedMethodParamIndex ? currMethodParamIndex == annotatedMethodParamIndex : true)
                    && (metadataKey ? currMetadataKey == metadataKey : true)
            }
        )
    }

    /**
     * 精确查找
     * @param affiliatedMetadataDomain
     * @param annotatedMethod
     * @param annotatedMethodParamIndex
     * @param metadataKey
     */
    public find(
        affiliatedMetadataDomain: Key,
        annotatedMethod: Key,
        annotatedMethodParamIndex: number,
        metadataKey: Key
    ): ParamMetadata | undefined {
        let result = this.findAll(
            affiliatedMetadataDomain,
            annotatedMethod,
            annotatedMethodParamIndex,
            metadataKey
        );
        if (result.length == 0) return undefined;
        return result[0];
    }

    /**
     * 添加
     * @param affiliatedMetadataDomain
     * @param annotatedMethod
     * @param metadataKey
     * @param annotatedMethodParamIndex
     * @param metadataValue
     */
    public add(
        affiliatedMetadataDomain: Key,
        annotatedMethod: Key,
        annotatedMethodParamIndex: number,
        metadataKey: Key,
        metadataValue: Value
    ): void {
        this.paramMetadataList.set(
            [affiliatedMetadataDomain, annotatedMethod, annotatedMethodParamIndex, metadataKey],
            new ParamMetadata(affiliatedMetadataDomain, annotatedMethod, annotatedMethodParamIndex, metadataKey, metadataValue)
        );
    }
}
