// @generated
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/com/facebook/buck/remoteexecution/proto/metadata.proto

package com.facebook.buck.remoteexecution.proto;

/**
 * <pre>
 * minimal worker requirements which should be satisfied to execute a given action
 * </pre>
 *
 * Protobuf type {@code facebook.remote_execution.WorkerRequirements}
 */
@javax.annotation.Generated(value="protoc", comments="annotations:WorkerRequirements.java.pb.meta")
public  final class WorkerRequirements extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:facebook.remote_execution.WorkerRequirements)
    WorkerRequirementsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use WorkerRequirements.newBuilder() to construct.
  private WorkerRequirements(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private WorkerRequirements() {
    hardwareType_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private WorkerRequirements(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {
            int rawValue = input.readEnum();

            hardwareType_ = rawValue;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadataProto.internal_static_facebook_remote_execution_WorkerRequirements_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadataProto.internal_static_facebook_remote_execution_WorkerRequirements_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.facebook.buck.remoteexecution.proto.WorkerRequirements.class, com.facebook.buck.remoteexecution.proto.WorkerRequirements.Builder.class);
  }

  /**
   * Protobuf enum {@code facebook.remote_execution.WorkerRequirements.WorkerHardwareType}
   */
  public enum WorkerHardwareType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>SMALL = 0;</code>
     */
    SMALL(0),
    /**
     * <code>MEDIUM = 1;</code>
     */
    MEDIUM(1),
    /**
     * <code>LARGE = 2;</code>
     */
    LARGE(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>SMALL = 0;</code>
     */
    public static final int SMALL_VALUE = 0;
    /**
     * <code>MEDIUM = 1;</code>
     */
    public static final int MEDIUM_VALUE = 1;
    /**
     * <code>LARGE = 2;</code>
     */
    public static final int LARGE_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static WorkerHardwareType valueOf(int value) {
      return forNumber(value);
    }

    public static WorkerHardwareType forNumber(int value) {
      switch (value) {
        case 0: return SMALL;
        case 1: return MEDIUM;
        case 2: return LARGE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<WorkerHardwareType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        WorkerHardwareType> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<WorkerHardwareType>() {
            public WorkerHardwareType findValueByNumber(int number) {
              return WorkerHardwareType.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.facebook.buck.remoteexecution.proto.WorkerRequirements.getDescriptor().getEnumTypes().get(0);
    }

    private static final WorkerHardwareType[] VALUES = values();

    public static WorkerHardwareType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private WorkerHardwareType(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:facebook.remote_execution.WorkerRequirements.WorkerHardwareType)
  }

  public static final int HARDWARE_TYPE_FIELD_NUMBER = 1;
  private int hardwareType_;
  /**
   * <code>.facebook.remote_execution.WorkerRequirements.WorkerHardwareType hardware_type = 1;</code>
   */
  public int getHardwareTypeValue() {
    return hardwareType_;
  }
  /**
   * <code>.facebook.remote_execution.WorkerRequirements.WorkerHardwareType hardware_type = 1;</code>
   */
  public com.facebook.buck.remoteexecution.proto.WorkerRequirements.WorkerHardwareType getHardwareType() {
    @SuppressWarnings("deprecation")
    com.facebook.buck.remoteexecution.proto.WorkerRequirements.WorkerHardwareType result = com.facebook.buck.remoteexecution.proto.WorkerRequirements.WorkerHardwareType.valueOf(hardwareType_);
    return result == null ? com.facebook.buck.remoteexecution.proto.WorkerRequirements.WorkerHardwareType.UNRECOGNIZED : result;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (hardwareType_ != com.facebook.buck.remoteexecution.proto.WorkerRequirements.WorkerHardwareType.SMALL.getNumber()) {
      output.writeEnum(1, hardwareType_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (hardwareType_ != com.facebook.buck.remoteexecution.proto.WorkerRequirements.WorkerHardwareType.SMALL.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, hardwareType_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.facebook.buck.remoteexecution.proto.WorkerRequirements)) {
      return super.equals(obj);
    }
    com.facebook.buck.remoteexecution.proto.WorkerRequirements other = (com.facebook.buck.remoteexecution.proto.WorkerRequirements) obj;

    boolean result = true;
    result = result && hardwareType_ == other.hardwareType_;
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + HARDWARE_TYPE_FIELD_NUMBER;
    hash = (53 * hash) + hardwareType_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.facebook.buck.remoteexecution.proto.WorkerRequirements prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * minimal worker requirements which should be satisfied to execute a given action
   * </pre>
   *
   * Protobuf type {@code facebook.remote_execution.WorkerRequirements}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:facebook.remote_execution.WorkerRequirements)
      com.facebook.buck.remoteexecution.proto.WorkerRequirementsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadataProto.internal_static_facebook_remote_execution_WorkerRequirements_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadataProto.internal_static_facebook_remote_execution_WorkerRequirements_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.facebook.buck.remoteexecution.proto.WorkerRequirements.class, com.facebook.buck.remoteexecution.proto.WorkerRequirements.Builder.class);
    }

    // Construct using com.facebook.buck.remoteexecution.proto.WorkerRequirements.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      hardwareType_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadataProto.internal_static_facebook_remote_execution_WorkerRequirements_descriptor;
    }

    @java.lang.Override
    public com.facebook.buck.remoteexecution.proto.WorkerRequirements getDefaultInstanceForType() {
      return com.facebook.buck.remoteexecution.proto.WorkerRequirements.getDefaultInstance();
    }

    @java.lang.Override
    public com.facebook.buck.remoteexecution.proto.WorkerRequirements build() {
      com.facebook.buck.remoteexecution.proto.WorkerRequirements result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.facebook.buck.remoteexecution.proto.WorkerRequirements buildPartial() {
      com.facebook.buck.remoteexecution.proto.WorkerRequirements result = new com.facebook.buck.remoteexecution.proto.WorkerRequirements(this);
      result.hardwareType_ = hardwareType_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.facebook.buck.remoteexecution.proto.WorkerRequirements) {
        return mergeFrom((com.facebook.buck.remoteexecution.proto.WorkerRequirements)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.facebook.buck.remoteexecution.proto.WorkerRequirements other) {
      if (other == com.facebook.buck.remoteexecution.proto.WorkerRequirements.getDefaultInstance()) return this;
      if (other.hardwareType_ != 0) {
        setHardwareTypeValue(other.getHardwareTypeValue());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.facebook.buck.remoteexecution.proto.WorkerRequirements parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.facebook.buck.remoteexecution.proto.WorkerRequirements) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int hardwareType_ = 0;
    /**
     * <code>.facebook.remote_execution.WorkerRequirements.WorkerHardwareType hardware_type = 1;</code>
     */
    public int getHardwareTypeValue() {
      return hardwareType_;
    }
    /**
     * <code>.facebook.remote_execution.WorkerRequirements.WorkerHardwareType hardware_type = 1;</code>
     */
    public Builder setHardwareTypeValue(int value) {
      hardwareType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.facebook.remote_execution.WorkerRequirements.WorkerHardwareType hardware_type = 1;</code>
     */
    public com.facebook.buck.remoteexecution.proto.WorkerRequirements.WorkerHardwareType getHardwareType() {
      @SuppressWarnings("deprecation")
      com.facebook.buck.remoteexecution.proto.WorkerRequirements.WorkerHardwareType result = com.facebook.buck.remoteexecution.proto.WorkerRequirements.WorkerHardwareType.valueOf(hardwareType_);
      return result == null ? com.facebook.buck.remoteexecution.proto.WorkerRequirements.WorkerHardwareType.UNRECOGNIZED : result;
    }
    /**
     * <code>.facebook.remote_execution.WorkerRequirements.WorkerHardwareType hardware_type = 1;</code>
     */
    public Builder setHardwareType(com.facebook.buck.remoteexecution.proto.WorkerRequirements.WorkerHardwareType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      hardwareType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.facebook.remote_execution.WorkerRequirements.WorkerHardwareType hardware_type = 1;</code>
     */
    public Builder clearHardwareType() {
      
      hardwareType_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:facebook.remote_execution.WorkerRequirements)
  }

  // @@protoc_insertion_point(class_scope:facebook.remote_execution.WorkerRequirements)
  private static final com.facebook.buck.remoteexecution.proto.WorkerRequirements DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.facebook.buck.remoteexecution.proto.WorkerRequirements();
  }

  public static com.facebook.buck.remoteexecution.proto.WorkerRequirements getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<WorkerRequirements>
      PARSER = new com.google.protobuf.AbstractParser<WorkerRequirements>() {
    @java.lang.Override
    public WorkerRequirements parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new WorkerRequirements(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<WorkerRequirements> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<WorkerRequirements> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.facebook.buck.remoteexecution.proto.WorkerRequirements getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

