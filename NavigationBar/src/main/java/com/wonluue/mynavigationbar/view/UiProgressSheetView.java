�}�&  :� 9��iR�l��#��� 
hz�o5��{�4��6!��3��u�C(���{� 9����@�
:��k�	�aF9�5�t���aɿ��\�$cg"�z���;1�b�	/I��@��r��Py���>,]Y%v�]ɋ�j�eQX�>?%.H����5����iJ��(��Mhb��J#nv�L��X��7 TX�HV��ˉW����z��T�b����øff��a췟����K�'�������8�҆v10�ŞG^��
b�����t�Gi�Y5 �s4ӡf%������q��\Y��[}��������v�?���B�bvNI�%d���$rxx�E1� '�B���@�>a��c =a�e��b��(�'S]@�N��C���XK��X�{~/��Z���&l�iu������Ҿ��C<�'��m�4TF#(������5��9H�aw�M�}��ᶚJ.�	�9s���(�mb�,�kf�j���doN�8��x�e�l*|=�� ��Jw�b��믪��똿��� ��!OAmhM-��l޼�r�g`�2�D����Vz�.����0�\�X�6����s������pi^���Ą��,Z�����d�����������O�8��bd�ۣ���C�@kLI/���W��Y�����# ��{kfo��MK�MyI��%�~!�H�&�wr��qh7���'�G�0�Pw>JLea$��4\#@-!_�Q���)��[����25�mG�����#]^vWj��@ ��� �����Mna܅j?9���yWq(��rڤ#�5�:�%���eK�-�~Ȇ��Wv�������5��/�|��n^8�(����g�*���K>�=���H��/�2/�� �O�;0B*��	��8A�4�;qt��g�|�B��N��1nd@s����:^덴D�vby�%�9;\�,�!m�1��!���Vr(�9�EAW qy��+���1UX��e�״w�2�����=�����       view = View.inflate(activity,R.layout.layout_progress_sheet_view,null);
        textView = (TextView) view.findViewById(R.id.textView);
        setView(view);
    }

    public UiProgressSheetView setMessage(String message){
        textView.setText(message);
        return this;
    }

    public UiProgressSheetView setMessage(int messageId){
        textView.setText(messageId);
        return this;
    }

    @Override
    public void show() {
        super.show();

    }

}
