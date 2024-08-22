

import type {Key} from "@/common/decoration/BusMap";
import {PropertyMetadataManager, PropSet} from "@/common/decoration/PropertyMetadata";
import {ParamMetadataManager} from "@/common/decoration/ParamMetadataManager";
import {MethodMetadataManager} from "@/common/decoration/MethodMetadataManager";
import {ClassMetadataManager} from "@/common/decoration/ClassMetadata";

export class MetadataCenter{
    /**
     * 元数据宿主类的类名
     */
    public readonly className: Key;

    public readonly propertyMetadataManager: PropertyMetadataManager;
    public readonly propSet: PropSet;
    public readonly paramMetadataManager: ParamMetadataManager;
    public readonly methodMetadataManager: MethodMetadataManager;
    public readonly classMetadataManager: ClassMetadataManager;

    constructor(klass: Function) {
        this.className = klass.name;
        this.propSet = new PropSet()
        this.propertyMetadataManager = new PropertyMetadataManager();
        this.paramMetadataManager = new ParamMetadataManager();
        this.methodMetadataManager = new MethodMetadataManager();
        this.classMetadataManager = new ClassMetadataManager();
    }

    private static readonly METADATA_CENTER = "MetadataCenter";

    public static getManager(klass: Function): MetadataCenter | undefined {
        return Reflect.getMetadata(this.METADATA_CENTER, klass);
    }
}