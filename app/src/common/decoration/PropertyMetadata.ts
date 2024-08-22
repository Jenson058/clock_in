import {BusMap} from "@/common/decoration/BusMap";
import type {Key,Value} from "@/common/decoration/BusMap";
import {ListUtil} from "@/util/ListUtil";

export class PropertyMetadata {
    /**
     * 该属性元数据所属的领域 (属性注解可以分属于)
     */
    public readonly affiliatedMetadataDomain: Key;
    /**
     * 所标注的类的属性
     */
    public readonly annotatedProperty: Key;

    public readonly metadataKey: Key;
    public readonly metadataValue: Value;

    constructor(
        affiliatedMetadataDomain: Key,
        annotatedProperty: Key,
        metadataKey: Key,
        metadataValue: Value
    ) {
        this.affiliatedMetadataDomain = affiliatedMetadataDomain;
        this.annotatedProperty = annotatedProperty;
        this.metadataKey = metadataKey;
        this.metadataValue = metadataValue;
    }
}

/**
 * 属性的元数据管理器
 */
export class PropertyMetadataManager {
    /**
     * [Key, Key, Key] 对应 [affiliatedMetadataDomain, annotatedProperty, metadataKey]
     * 当前该属性属性 public 的, 当 find 函数完善之后可以转为 private
     */
    public readonly propertyMetadataList: BusMap<[Key, Key, Key], PropertyMetadata>;

    constructor() {
        this.propertyMetadataList = new BusMap<[Key, Key, Key], PropertyMetadata>;
    }

    /**
     * todo 在模糊搜索的基础之上, 扩充一系列常用的精确搜索函数
     * 模糊搜索
     * @param affiliatedMetadataDomain
     * @param annotatedProperty
     * @param metadataKey
     */
    public findAll(affiliatedMetadataDomain?: Key, annotatedProperty?: Key, metadataKey?: Key): Array<PropertyMetadata> {
        return this.propertyMetadataList.filter(
            ([currAffiliatedMetadataDomain, currAnnotatedProperty, currMetadataKey]) => {
                return (affiliatedMetadataDomain ? currAffiliatedMetadataDomain == affiliatedMetadataDomain : true)
                    && (annotatedProperty ? currAnnotatedProperty == annotatedProperty : true)
                    && (metadataKey ? currMetadataKey == metadataKey : true)
            }
        )
    }

    /**
     * 精确查找
     * @param affiliatedMetadataDomain
     * @param annotatedProperty
     * @param metadataKey
     */
    public find(affiliatedMetadataDomain: Key, annotatedProperty: Key, metadataKey: Key): PropertyMetadata | undefined {
        let result = this.findAll(affiliatedMetadataDomain, annotatedProperty, metadataKey);
        if (result.length == 0) return undefined;
        return result[0];
    }

    /**
     * 添加
     * @param affiliatedMetadataDomain
     * @param annotatedProperty
     * @param metadataKey
     * @param metadataValue
     */
    public add(
        affiliatedMetadataDomain: Key,
        annotatedProperty: Key,
        metadataKey: Key,
        metadataValue: Value
    ): void {
        this.propertyMetadataList.set(
            [affiliatedMetadataDomain, annotatedProperty, metadataKey],
            new PropertyMetadata(affiliatedMetadataDomain, annotatedProperty, metadataKey, metadataValue)
        );
    }

    public get(key: string) {
        return this.find(key.split("::")[0], key.split("::")[1], key.split("::")[2])
    }
}

export class PropSet {
    public pSet: Map<string, Array<string>>


    constructor() {
        this.pSet = new Map<string, Array<string>>();
    }

    public get(key: string) {
        if (!this.pSet.get(key)) {
            this.pSet.set(key, [])
        }

        return this.pSet.get(key)
    }

    public set(key: string, value: string) {
        if (!this.pSet.get(key)) {
            this.pSet.set(key, [])
        }
        if (!ListUtil.exist(this.pSet.get(key), value)) {
            this.pSet.get(key)?.push(key + "::" + value)
        }
    }
}