import org.bougainvilleas.resource.bundle.first.*;

module resource_bundle {
    exports org.bougainvilleas.resource.bundle.first;
    uses SourceProvider;
    provides SourceProvider with
            ZHCNSourceProvider,
            ZHSourceProvider,
            ENSourceProvider,
            ENUSSourceProvider;
}